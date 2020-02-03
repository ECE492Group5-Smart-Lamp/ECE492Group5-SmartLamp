import RPi.GPIO as GPIO
import time
from time import sleep     # Import the sleep function from the time module

GPIO.setwarnings(False)    # Ignore warning for now
# difference in GPIO.BOARD / GPIO.BCM (will be useful in future)
# https://raspberrypi.stackexchange.com/questions/12966/what-is-the-difference-between-board-and-bcm-for-gpio-pin-numbering
GPIO.setmode(GPIO.BOARD)   # Use physical pin numbering
GPIO.setup(8, GPIO.OUT, initial=GPIO.LOW)   # Set pin 8 to be an output pin and set initial value to low (off)

print "LED on"
GPIO.output(8,GPIO.HIGH)
time.sleep(1)
print "LED off"
GPIO.output(8,GPIO.LOW)
time.sleep(5)

while True: # Run forever
    GPIO.output(8, GPIO.HIGH) # Turn on
    sleep(10)                  # Sleep for 1 second
    GPIO.output(8, GPIO.LOW)  # Turn off
    sleep(3)                  # Sleep for 1 second
    GPIO.output(8, GPIO.HIGH) # Turn on
    sleep(10)  
    GPIO.output(8, GPIO.LOW)  # Turn off
    sleep(3)   
    GPIO.output(8, GPIO.HIGH) # Turn on
    sleep(10)  
    GPIO.output(8, GPIO.LOW)  # Turn off
    sleep(3)
    GPIO.output(8, GPIO.HIGH) # Turn on
    sleep(1)  
    GPIO.output(8, GPIO.LOW)  # Turn off
    sleep(1)
    GPIO.output(8, GPIO.HIGH) # Turn on
    sleep(1)  
    GPIO.output(8, GPIO.LOW)  # Turn off
    sleep(1)

