# creation date: Feb 14 2020
# author: group 5
# contents of the file: source code used to control the LED on Raspberry Pi

import board
import neopixel
import time
pixels = neopixel.NeoPixel(board.D18, 12,auto_write = False)

class LED:
    def __init__(self, brightness_level):
        
        self.brighteness_level = brightness_level
        
    def displayLight(self,color, brightness_level):
    
        pixels.fill((int(brightness_level*color.red),int(brightness_level*color.green),int(brightness_level*color.blue)))
        pixels.show()
        
    def turnoff(self):
        pixels.fill((0,0,0))
        pixels.show()
        
class Color:
    def __init__(self):
        self.red = 255
        self.green = 255
        self.blue = 255
        
    def setColor(self,color_num):
        if color_num == "White":
            self.red = 255
            self.green = 255
            self.blue = 255
        elif color_num == "Yellow":
            self.red = 255
            self.green = 255
            self.blue = 0
        elif color_num == "Pink":
            self.red = 255
            self.green = 105
            self.blue = 108       
        elif color_num == "Green":
            self.red = 0
            self.green = 255
            self.blue = 127
    
    def setSelfColor(self, redNum, greenNum, blueNum):
        self.red = redNum
        self.green = greenNum
        self.blue = blueNum
