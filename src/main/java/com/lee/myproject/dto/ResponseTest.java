package com.lee.myproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// 다른 외부로 보낼 때 사용? 아니면 이게 원래 디폴트?
//@Data
@Getter
@AllArgsConstructor
public class ResponseTest<T> {

    /**
     * 메세지
     * 응답 코드
     * 데이터
     *
     */
    private int code;
    private String message;
    private List<T> data;

    // 재사용성
    // 유연성
    // 일반화된 응답방식
    // 가독성 및 유지보수성
    // JSON 직렬화 및 역 직렬화

    public ResponseTest() {

    }

    public ResponseTest(List<T> data) {

        this.data = data;

        if (data == null) {
            this.code = 10;
            this.message = "NODATA";
        } else if (data != null) {
            this.code = 0;
            this.message = "SUCCESS";
        } else {
            this.code = 1;
            this.message = "ERROR";
        }
    }

    public ResponseTest(int code) {
        this.code = code;

        switch (code) {
            case 0:
                this.message = "SUCCESS";
                break;
            case 1:
                this.message = "ERROR";
                break;
            case 2:
                this.message = "UPDATE";
                break;
            case 10:
                this.message = "NODATA";
                break;
        }

    }

    //    public ResponseTest(T data2) {
//
//        this.data2 = data2;
//
//        if (data2 == null) {
//            this.code = 10;
//            this.message = "NODATA";
//        } else if (data2 != null) {
//            this.code = 0;
//            this.message = "SUCCESS";
//        } else {
//            this.code = 1;
//            this.message = "ERROR";
//        }
//    }
}
