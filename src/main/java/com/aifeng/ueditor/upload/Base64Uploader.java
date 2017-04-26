package com.aifeng.ueditor.upload;

import com.aifeng.ueditor.PathFormat;
import com.aifeng.ueditor.define.AppInfo;
import com.aifeng.ueditor.define.BaseState;
import com.aifeng.ueditor.define.FileType;
import com.aifeng.ueditor.define.State;
import com.aifeng.util.PropUtil;
import org.apache.commons.codec.binary.Base64;

import java.util.Map;

public final class Base64Uploader {

    public static State save(String content, Map<String, Object> conf) {

        byte[] data = decode(content);

        long maxSize = ((Long) conf.get("maxSize")).longValue();

        if (!validSize(data, maxSize)) {
            return new BaseState(false, AppInfo.MAX_SIZE);
        }

        String suffix = FileType.getSuffix("JPG");

        String savePath = PathFormat.parse((String) conf.get("savePath"),
                (String) conf.get("filename"));

        savePath = savePath + suffix;
        String physicalPath = (String) PropUtil.getString("file.path") + savePath;

        State storageState = StorageManager.saveBinaryFile(data, physicalPath);

        if (storageState.isSuccess()) {
            storageState.putInfo("url", "../pic/showPic.cs?fileName=" + PathFormat.format(savePath));
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", "");
        }

        return storageState;
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(content);
    }

    private static boolean validSize(byte[] data, long length) {
        return data.length <= length;
    }

}