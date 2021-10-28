package com.firekcc.springcloud.configserver.handler;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;

@RestController
public class Knife4jHandlerController {

    @GetMapping(value = {"/webjars/**/*.css", "/webjars/**/*.js", "/webjars/**/*.png"})
    public void swagger(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        try {

            String fullPath = "META-INF/resources" + request.getRequestURI();

            InputStream inputStream = Objects.requireNonNull(new DefaultResourceLoader().getClassLoader()).getResourceAsStream(fullPath);

            download(response.getOutputStream(), inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void download(OutputStream out, InputStream inputStream) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream = new BufferedInputStream(inputStream);

            bufferedOutputStream = new BufferedOutputStream(out);

            byte[] b = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(b)) > 0) {
                bufferedOutputStream.write(b, 0, length);
                bufferedOutputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}