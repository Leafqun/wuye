package com.wuye.manage.wuye.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: xiaoyu
 * @description:
 * @author: Leafqun
 * @date: 2018-07-16 18-23-02
 **/

@Component
public class SSHConnection {

    private Session session;

    @Resource
    private SSH ssh;

    public void connect() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(ssh.getUser(), ssh.getHost(), ssh.getPort());
            session.setPassword(ssh.getPwd());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            session.setPortForwardingL(ssh.getLocalPort(), ssh.getRemoteHost(), ssh.getRemotePort());
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        session.disconnect();
    }

}
