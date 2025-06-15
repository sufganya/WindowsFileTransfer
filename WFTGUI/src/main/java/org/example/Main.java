package org.example;

import java.io.IOException;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    final static String hostIP = "192.168.1.194";
    final static String username = "einav";
    final static String password = "2121";
    final static String filePath = "C:/Users/user/Desktop/Yuval.txt";

    public static void main(String[] args) {
        try {
            SSHClient client = new SSHClient();
            client.addHostKeyVerifier(new PromiscuousVerifier());
            client.connect(hostIP);
            client.authPassword(username, password);
            SFTPClient sftp = client.newSFTPClient();
            sftp.put(new FileSystemFile(filePath), "/Users/einav/Desktop");
            sftp.close();
            client.disconnect();
            System.out.println("File transferred successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static SSHClient connect(String hostIP, String username, String password) throws IOException {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect(hostIP);
        client.authPassword(username, password);
        return client;
    }

    public static void disconnect(SSHClient client) {
        try {
            client.disconnect();
        } catch (Exception e) {
            System.out.println("Disconnection failed");
        }
    }
}