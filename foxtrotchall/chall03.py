from pwn import *
import string
# if input = "ABCDEFGHIJKLMNOP"
# got_string = "q7v0ZQwtZbgnZqBFCjvWSDQ0llgn"
# another = "3V+5XQaBUj198ReGrM42yHgmnF0WtAOEoliZkqwIpcN6xLSCYhfsz7vdubTP/DJK"
pass_word = "ahah_nobodycancrackme"
password = []
for i in range(50):
    for c in string.printable:
        d = ''.join((''.join(password),c))
        p = process('./chall03')
        p.sendline(d)
        x = p.recv(1024)
        if "SUCCESS" in x:
            password.append(c)
        p.close()

    print(''.join(password))
