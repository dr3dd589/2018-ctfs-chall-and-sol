from pwn import *
from itertools import *
import time
def strp(a):
	f=""
	for i in a:
		f=f+str(i)
	return f

r=remote("199.247.6.180",18000)


print "[+] Task 1 started"

r.recvuntil("What am I?(one word)")
r.sendline("secret")

print "[+] Task 1 completed"


print "[+] Task 2 started"

combinations=list(product(range(0,10),repeat=7))
r.recvuntil("hashes:")
r.recvline()
hashes=[]
cracked={}
for i in range(10):
	hashes.append(int(r.recvline()))
for i in combinations:
	temp_hash1=(strp(i)+'stealer')
	temp_hash2=('stealer'+strp(i))
	for j in hashes:
		if j==hash(temp_hash1):
			cracked[j]=temp_hash1
		if j==hash(temp_hash2):
			cracked[j]=temp_hash2
	if(len(cracked)==10):
		break						

print "[+] hashes cracked"

for i in hashes:
	r.sendline(str(cracked[i]))
	time.sleep(0.2)
print "[+] Task 2 completed"



print "[+] Task 3 started"
r.recvuntil("%")
mod=int((r.recvline())[2:])
ans=(17*(666013**3))%mod
r.sendline(str(ans))
print "[+] Task 3 completed"



print "[+] Task 4 started"

'''download the image from link given and then there is zip embedded 
inside the image in which there is another image in which one more file
is there with corrupted header after fixing header we get another image
which gives password sternocleidomastoidian''' 

r.recvuntil("HRwM0jU.png")
r.sendline("sternocleidomastoidian")

print "[+] Task 4 completed"



print "[+] Task 5 started"

r.recvuntil("HRyG0yE.png")
r.sendline("this_is_not_a_red_herring")

print "[+] Task 5 completed"

r.recvline()
r.recvline()
print "[+] Flag is:  "+r.recvline()
r.close()