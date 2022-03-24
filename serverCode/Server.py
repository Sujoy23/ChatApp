import socket
from threading import *

def client_thread(r_from, s_to):
    while True:
        data = r_from.recv(50).decode()
        print("data from connection :" + str(data))
        s_to.send(data.encode())
        s_to.send("\n".encode())
        if(data == "Over"):
            break
    r_from.close()
    print("Connection closed from :" + str(r_from))

def server_program():
    # get the hostname
    host = socket.gethostname()
    port = 4444  # initiate port no above 1024

    server_socket = socket.socket()  # get instance
    # look closely. The bind() function takes tuple as argument
    server_socket.bind((host, port))  # bind host address and port together
    print("Server Started at : " + str(host) + " " + str(port))
    # configure how many client the server can listen simultaneously
    server_socket.listen(2)
    wait_queue = {}

    while True:
        conn, address = server_socket.accept()  # accept new connection
        print("1st Connection from: " + str(address))

        data = conn.recv(50).decode()
        connect_to, connect_from = data.split(':')
        if(connect_to == wait_queue[connect_from]):
            conn2 = wait_queue[connect_from]
            t1 = Thread(target=client_thread, args=(conn, conn2,))
            t1.daemon = True
            t2 = Thread(target=client_thread, args=(conn2, conn,))
            t2.daemon = True
            t1.start()
            t2.start()
            del wait_queue[connect_from]
        else:
            wait_queue[connect_to] = conn
            conn.send("Waiting for next user to connect..\n".encode())





    print("Connections closed from both end")


if __name__ == '__main__':
    server_program()