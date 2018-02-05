package com.example.giangdam.domain.config;

/**
 * Created by cpu11326-local on 31/01/2018.
 * Config file dùng để On/Of các setting.
 */

public class DeveloperConfig {

    // Có được phép in log hay không?
    private static final boolean ABLE_TO_LOG = true;

    // Bật tắt giả lập trễ mạng.
    private static final boolean ABLE_TO_MIMIC_INTERNET_DELAY = false;

    // Danh sách các Client Library dùng để gọi tới à festAPI.
    public enum CLOUD_CLIENT_LIBRARY {
        CALLABLE, RETROFIT
    }

    public static boolean ableToLog(){
        return ABLE_TO_LOG;
    }

    public static boolean ableToMimicInternetDelay() {
        return ABLE_TO_MIMIC_INTERNET_DELAY;
    }


    /*
    * Client library đang được sử dụng để gọi RestAPI.
     */
    public static CLOUD_CLIENT_LIBRARY currentCloudClientLibrary() {
        return CLOUD_CLIENT_LIBRARY.RETROFIT;
    }



    /*
        Vì đây là file config, nên chắc chắn nó ko có logic nào
        nên ko nhất thiết phải #Private biến rồi dùng hàm get
        cứ chấm trực tiếp
        ( Kiểu như View.VISIBLE hoặc View.GONE )
     */
    public interface DEBUG_CONFIG{
        boolean IS_PRODUCTION = true; /* Gọi API product hay dev ( nếu có thêm môi trường staging thì phải là enum ) */

        boolean IS_SHOW_LOG_API = true;     // Show API log hay ko ?

        boolean IS_NETWORK_DELAY = true;    // Có Giả lập mạng chậm ko ?
        int NET_WORK_DELAY_TIME = 1000;     // Thời gian giả lập mạng delay ( in millisecond )

        boolean IS_SHOW_SOME_THING = true;  // 1 vài config debug khác


    }
    public interface REST_CLIENT{
        enum RestClientLib {
            CALLABLE,
            RETROFIT
        }

        RestClientLib CURRENT_REST_CLIENT = RestClientLib.RETROFIT;
    }

    public interface APP_CONFIG{
        boolean IS_MERGE_USER_FORM_ALL_REPO = true;

    }





}
