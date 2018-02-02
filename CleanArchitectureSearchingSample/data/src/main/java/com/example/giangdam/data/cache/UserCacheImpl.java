package com.example.giangdam.data.cache;

import android.content.Context;

import com.example.giangdam.data.converter.JsonConverter;
import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.data.file.FileManager;
import com.example.giangdam.domain.thread.SubcribeOnThread;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by cpu11326-local on 30/01/2018.
 * Cache data here
 */

public class UserCacheImpl implements UserCache {

    private static final String SETTING_FILE_NAME = "com.example.giangdam.data.cache.SETTING";
    private static final String SETTING_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_list_";
    private static final long EXPIRATION_TIME = 60 * 10 * 100;

    private final Context context;
    private final File cacheDir;
    private final JsonConverter converter;
    private final FileManager fileManager;
    private final SubcribeOnThread threadExecutor;

    @Inject
    UserCacheImpl(Context context, JsonConverter jsonConverter, FileManager fileManager,
                  SubcribeOnThread threadExecutor) {
        if(context == null || jsonConverter == null || fileManager == null ||
                threadExecutor == null) {
            throw  new IllegalArgumentException("Invalid null parameter");
        }

        this.context = context;
        this.cacheDir = context.getCacheDir();
        this.converter = jsonConverter;
        this.fileManager = fileManager;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<List<UserEntity>> get() {
        return Observable.create(new ObservableOnSubscribe<List<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserEntity>> emitter) throws Exception {
                // build cache file
                final File userEntityListFile = UserCacheImpl.this.buildFile();

                // đọc file content.
                final String fileContent = UserCacheImpl.this.fileManager.readFileContent(userEntityListFile);

                // convert jsonString sang List<UserEntity>.
                final List<UserEntity> userEntityList = UserCacheImpl.this.converter.jsonToListObject(fileContent);

                if(userEntityList != null) {
                    emitter.onNext(userEntityList);
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("User in Cache null"));
                }
            }
        });
    }

    /**
     * Build cache file.
     * @return
     */
    public File buildFile() {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Cache lại data
     * @param userEntityList
     */
    @Override
    public void put(List<UserEntity> userEntityList) {
        if(userEntityList != null) {
            // Build cache file.
            final  File userEntityListFile = this.buildFile();

            // Kiểm tra xem file đã được cache hay chưa, nếu chưa cache thì tiến hành cache
            if(!isCached()) {
                // convert list Object sang json string.
                final String jsonString = this.converter.listObjectToJson(userEntityList);

                // Ghi cache asynchronously.
                this.executeAsynchronously(new CacheWriter(this.fileManager,
                        userEntityListFile, jsonString));

                // update thời gian update cache.
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    /**
     * Kiểm tra dữ liệu đã được cache hay chưa.
     * @return
     */
    @Override
    public boolean isCached() {
        final File userEntityListFile = this.buildFile();
        return this.fileManager.exists(userEntityListFile);
    }

    /**
     * xác định cache đã expire hay chưa.
     * @return
     */
    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if(expired) {
            this.evictAll();
        }

        return expired;
    }


    /**
     * Xóa hết cache.
     */
    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTING_FILE_NAME,
                SETTING_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTING_FILE_NAME,
                SETTING_KEY_LAST_CACHE_UPDATE);
    }

    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    private static class CacheWriter implements Runnable {

        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File file, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = file;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    private static class CacheEvictor implements Runnable {

        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File file) {
            this.fileManager = fileManager;
            this.cacheDir = file;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
