
flag = "CTF{XXXXXXXXXXXXXXXXXXXX}"

def main():
	
	print "Welcome to the ADVANCED login portal, username=admin\nEnter Password:\n"

	count = 1

	while True:
		print "Enter character number {}: ".format(count)
		char = raw_input()[0]
		if count == len(flag):
			break
		if char != flag[count-1]:
			print "Nah, you entered wrong character. I saved your time!!"
			break
		count+=1

if __name__=="__main__":
	main()
