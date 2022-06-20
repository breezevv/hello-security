package example.hellosecurity;

import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.nio.charset.StandardCharsets;

public class JwtTest {

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJsb2dpblRpbWUiOjE2NTU2MTM4MjAsInVzZXJuYW1lIjoidXNlckBleGFtcGxlLmNvbSJ9.fP9r-Q9JbUyCgXQqJvwyABATtRs81SKImginkJRqqDLb2aSSNvuV1U8YpqqkAliF2vAkjQvhba3C8pN_ZckrrQ";
        JWTSigner jwtSigner = JWTSignerUtil.hs512("yingyingying".getBytes(StandardCharsets.UTF_8));
        System.out.println(JWTUtil.verify(token, jwtSigner));
    }
}
