# 2018-ctfs-chall-and-sol


 
<h2>Practice Resources</h2>

[1] - **Youtube Channel Links** 

  ::- [LiveOverflow](https://www.youtube.com/channel/UClcE-kVhqyiHCcjYwcpfj9w)<br>
	::- [GynvaelEN](https://www.youtube.com/user/GynvaelEN)<br>
	::- [MurmusCTF](https://www.youtube.com/c/MurmusCTF)<br>
	::- [CyberspaceCamp](https://www.youtube.com/c/CyberspaceCamp)<br>
	::- [IppSec (HackTheBox Tutorial)](https://www.youtube.com/channel/UCa6eh7gCkpPo5XXUDfygQQA)<br>
	::- [John Hammond (Beginners)](https://www.youtube.com/channel/UCVeW9qkBjo3zosnqUbG7CFw)<br>
	::- [JackkTutorials](https://www.youtube.com/user/JackkTutorials)<br>
  ::- [OWASP Talks](https://www.youtube.com/channel/UCe8j61ABYDuPTdtjItD2veA)<br>
  ::- [BlackHat Talks](https://www.youtube.com/channel/UCJ6q9Ie29ajGqKApbLqfBOg)<br><br>





```
from pwn import *

context.terminal = ["terminator", "-e"]

p = process(['./ld-2.23.so', './stkof'], env = {'LD_PRELOAD': '/home/dr3dd/practice/heap/libc-2.23.so'})
gdb.attach(p)

```
