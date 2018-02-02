package com.example.giangdam.data.file;

/**
 * Created by cpu11326-local on 29/01/2018.
 * Fake data class.
 */

public class UserData {
    public String userName;
    public String email;

    public UserData(){

    }

    public UserData(String userName, String email){
        this.userName = userName;
        this.email = email;
    }


    /**
     * Fake data.
     */
    public static UserData[] UserData = {
            new UserData("Khoa Hồ"        , "khoa.ho@gmail.com"),
            new UserData("Khoa Nguyễn"    , "khoa.nguyen@gmail.com"),
            new UserData("Khoa Bùi"       , "khoa.bui@gmail.com"),
            new UserData("Khoa Võ"        , "khoa.vo@gmail.com"),
            new UserData("Khoa Huỳnh"     , "khoa.huynh@gmail.com"),
            new UserData("Khoa Trần"      , "khoa.tran@gmail.com"),
            new UserData("Khoa Nguyễn 2"  , "khoa.nguyen2@gmail.com"),
            new UserData("Khoa Phan"      , "khoa.phan@gmail.com"),

            new UserData("Giang Hồ"       , "giang.ho@gmail.com"),
            new UserData("Giang Nguyễn"   , "giang.nguyen@gmail.com"),
            new UserData("Giang Bùi"      , "giang.bui@gmail.com"),
            new UserData("Giang Phan"     , "giang.phan@gmail.com"),

            new UserData("Tuấn Hồ"       , "tuan.ho@gmail.com"),
            new UserData("Tuấn Nguyễn"   , "tuan.nguyen@gmail.com"),
            new UserData("Tuấn Võ"       , "tuan.vo@gmail.com"),
            new UserData("Tuấn Huỳnh"    , "tuan.huynh@gmail.com"),
            new UserData("Tuấn Trần"     , "tuan.tran@gmail.com"),

            new UserData("Trúc Trần"     , "truc.tran@gmail.com"),
            new UserData("Trúc Nguyễn 2" , "truc.nguyen2@gmail.com"),
            new UserData("Trúc Phan"     , "truc.phan@gmail.com"),

            new UserData("Dũng Hồ"        , "dung.ho@gmail.com"),
            new UserData("Dũng Nguyễn"    , "dung.nguyen@gmail.com"),
            new UserData("Dũng Bùi"       , "dung.bui@gmail.com"),
            new UserData("Dũng Võ"        , "dung.vo@gmail.com"),
            new UserData("Dũng Huỳnh"     , "dung.huynh@gmail.com"),
            new UserData("Dũng Phan"      , "dung.phan@gmail.com"),
    };
}
