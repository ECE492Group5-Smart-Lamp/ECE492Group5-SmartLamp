# creation date: Feb 28 2020
# original author: Gus, https://pimylifeup.com/raspberry-pi-light-sensor/
# author of modification: group 5
# content of the file: brightness dectection using a photoresistor

import RPi.GPIO as GPIO
import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
pin_to_circuit = 16

def rc_time(pin_to_circuit):
    count = 0
    GPIO.setup(pin_to_circuit, GPIO.OUT)
    GPIO.output(pin_to_circuit, GPIO.LOW)
    time.sleep(0.1)
    
    GPIO.setup(pin_to_circuit, GPIO.IN)
    
    while(GPIO.input(pin_to_circuit) == GPIO.LOW):
        count += 1
    return count

while True:
        print(rc_time(pin_to_circuit))


