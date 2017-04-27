package com.imlsz.utils;

import com.google.common.net.InetAddresses;
import com.google.common.primitives.UnsignedInteger;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lsz on 2017/4/27.
 */
public class IDFactory {
    private long side = 0;
    private AtomicLong id = new AtomicLong( System.currentTimeMillis());
    private SpinLock lock  = new SpinLock();
    public IDFactory()  {
        try {
            loadGroup();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public long  next(){
        return id.addAndGet(side);
    }
    public void loadGroup() throws SocketException, UnknownHostException {
        side = InetAddresses.coerceToInteger(getLocalIntAddr())&0x0FFFFFFFFl;

    }
    /**
     * 获取localip 排除ipv6以及回路127.0.0.1
     * @return
     * @throws java.net.SocketException
     * @throws java.net.UnknownHostException
     */
    private InetAddress getLocalIntAddr() throws SocketException, UnknownHostException {
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                if(i.getAddress().length ==4 && i.getHostAddress().indexOf("127")!=0){
                    return i;
                }
            }
        }
        return  InetAddress.getLocalHost();
    }

    public static void main(String[] args) {
        IDFactory idFactory = new IDFactory();
        for (int i = 0 ; i< 1000;i++){
            System.out.println(idFactory.next());
        }
    }
}
