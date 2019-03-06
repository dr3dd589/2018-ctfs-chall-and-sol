import frida
import sys
import time

pid = frida.spawn("./catana")
# print("pid =", pid)
session = frida.attach(pid)

script = session.create_script("""
'use strict';
Interceptor.replace(ptr('0x00400556'), new NativeCallback(function(j,a,b) {
	//Thread.sleep(10);
	var CATALANS = ['1', '1', '2', '5', '14', '42', '132', '429', '1430', '4862', '16796', '58786', '208012', '742900', '2674440', '9694845', '35357670', '129644790', '477638700', '1767263190', '6564120420', '24466267020', '91482563640', '343059613650', '1289904147324', '4861946401452', '18367353072152', '69533550916004', '263747951750360', '1002242216651368', '3814986502092304', '14544636039226909', '55534064877048198', '212336130412243110', '812944042149730764', '3116285494907301262', '11959798385860453492'];
	var x = new Int64(CATALANS[j]);
	//console.log('CATALAN '+x);
	return x;
},'int64', ['int64','int64','int64']));

""")

def on_message(message, data):
	print(message)
script.on('message', on_message)

done = False
def on_detached_with_reason(reason):
    global done
    assert reason == 'process-terminated'
    done = True
session.on('detached', on_detached_with_reason)

script.load()
frida.resume(pid)
while not done:
    time.sleep(1)
