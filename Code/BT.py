import bluetooth
import LED

server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
port = 1
server_sock.bind(("",port))
server_sock.listen(1)
client_sock,address = server_sock.accept()
print ("Accepted connection from" , address)
while True:
    recvdata = client_sock.recv(1024)
    recvStr = str(recvdata, 'utf-8')
    brightness_level = 0.6
    initColor = TestLED.Color()
    User1 = TestLED.LED(brightness_level)
    print ("Received \"%s\" through Bluetooth" % recvdata)
    
    if recvStr == "on":
        User1.displayLight(initColor, brightness_level)
        print("on")
    if recvStr == "off":
        print("off")
        User1.turnoff()
    if 'brightness' in recvStr:
        print(recvStr)
        brightnessString = recvStr[11:-1]
        brightness_level = float(brightnessString)
        User1.displayLight(initColor, brightness_level)    
    if 'red' in recvStr:
        print(recvStr)
        redString = recvStr[4:-1]
        redNum = int(redString)
    if 'blue' in recvStr:
        print(recvStr)
        blueString = recvStr[5:-1]
        blueNum = int(blueString)
    if 'green' in recvStr:
        print(recvStr)
        greenString = recvStr[6:-1]
        greenNum = int(greenString)
    if (recvdata == "Q"):
        print("exiting")
        break
    
client_sock.close()
server_sock.close()