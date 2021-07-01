package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class represents a multi-threaded server
 */
public class TcpServer {
    private final int port;
    private volatile boolean stopServer;
    private ThreadPoolExecutor executor;
    private IHandler requestConcreteIHandler;
    ReentrantReadWriteLock locker;

    public TcpServer(int port) {
        this.port = port;
        stopServer = false;
        executor = null;
        locker=new ReentrantReadWriteLock();
    }

    public void run(IHandler concreteIHandlerStrategy) {
        this.requestConcreteIHandler = concreteIHandlerStrategy;

        Runnable mainLogic = () -> {
            try {
                executor = new ThreadPoolExecutor(
                        3, 5, 10,
                        TimeUnit.SECONDS, new PriorityBlockingQueue<>());
                ServerSocket server = new ServerSocket(port);
                server.setSoTimeout(1000);
                while (!stopServer) {
                    try {
                        Socket request = server.accept();
                        System.out.println("New connection to client established");
                        Runnable runnable = () -> {
                            try {
                                requestConcreteIHandler.handle(request.getInputStream(),
                                                               request.getOutputStream());

                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            } finally {   // Close all streams
                                try {
                                    request.getInputStream().close();
                                    request.getOutputStream().close();
                                    request.close();
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());

                                }

                            }
                        };
                        executor.execute(runnable);
                    } catch (SocketTimeoutException ignored) {
                    }
                }
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(mainLogic).start();
    }


    public void stop() {
        try {
            locker.writeLock().lock();
            if (!stopServer) {        //critical code section
                stopServer = true;
                if (executor != null) {
                    executor.shutdown();
                }
            }
        }
        finally {
            locker.writeLock().unlock();
        }
    }




    public static void main(String[] args) {
        System.out.println("Server is On");
        TcpServer tcpServer = new TcpServer(8081);
        tcpServer.run(new MatrixIHandler());

    }

}
