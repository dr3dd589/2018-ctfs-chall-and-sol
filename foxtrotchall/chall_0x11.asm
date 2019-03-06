.loop:
	mov      dl,byte [rsi]
	xor      dl,byte [rdi]
	inc      rsi
	inc      rdi
	or       al,dl
	loop     .loop


single byte xor of two strings which is in rsi and rdi and save it in dl and then bitwise or with al after that move to next char of rsi and rdi by increasing their value of index .

lets exp:

rsi = "asdf"
rdi = "ghjk"
al = 5

psudocode

for i in range(len(rsi)):
    al = al | (ord(rsi[i])^ord(rdi[i]))


result = 31
