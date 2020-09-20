package com.yamada.liveviewer.utils;

import com.yamada.liveviewer.vo.ResultVO;
import org.springframework.http.ResponseEntity;

public class ResultUtil {

    /**
     * 返回状态码为200的成功
     * @param data
     * @return
     */
    public static ResponseEntity<Object> success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMessage("成功");
        resultVO.setData(data);
        return ResponseEntity.status(200).body(resultVO);
    }

    /**
     * 返回除200外的成功
     * @param code
     * @param data
     * @return
     */
    public static ResponseEntity<Object> success(Integer code, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage("成功");
        resultVO.setData(data);
        return ResponseEntity.status(code).body(resultVO);
    }

    /**
     * 返回错误
     * @param code
     * @param message
     * @return
     */
    public static ResponseEntity<Object> error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return ResponseEntity.status(code).body(resultVO);
    }
}
