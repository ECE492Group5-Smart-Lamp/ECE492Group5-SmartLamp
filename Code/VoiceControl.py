# creation date: Feb 13 2020
# original author: Snowboy
# author of modification: group 5
# contents of the file: source code used for voice control option

import snowboydecoder
import sys
import signal

# Demo code for listening two hotwords at the same time

interrupted = False


def signal_handler(signal, frame):
    global interrupted
    interrupted = True


def interrupt_callback():
    global interrupted
    return interrupted

if len(sys.argv) != 3:
    print("Error: need to specify 2 model names")
    print("Usage: python demo.py 1st.model 2nd.model")
    sys.exit(-1)

models = ["Turn_on.pmdl","Turn_off.pmdl","Green.pmdl","Yellow.pmdl","White.pmdl","Red.pmdl"]

# capture SIGINT signal, e.g., Ctrl+C
signal.signal(signal.SIGINT, signal_handler)

sensitivity = [0.5]*len(models)
detector = snowboydecoder.HotwordDetector(models, sensitivity=sensitivity)
callbacks = [lambda: snowboydecoder.turn_on(),
             lambda: snowboydecoder.turn_off(),
             lambda: snowboydecoder.change_color("Green"),
             lambda: snowboydecoder.change_color("Yellow"),
             lambda: snowboydecoder.change_color("White"),
             lambda: snowboydecoder.change_color("Red"),]
print('Listening... Press Ctrl+C to exit')

# main loop
# make sure you have the same numbers of callbacks and models
detector.start(detected_callback=callbacks,
               interrupt_check=interrupt_callback,
               sleep_time=0.03)

detector.terminate()
