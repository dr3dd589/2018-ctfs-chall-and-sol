file = open("BlindDate.jpeg","r")
line = file.read()
n = 4
arr = [line[i:i+n] for i in range(0, len(line), n)]
file_new = []
for i in range(0,len(arr)):
	new_arr = arr[i]
	new_arr = new_arr[::-1]
	file_new.append(new_arr)

file = "".join(file_new)
newfile = open("file_01","w")
newfile.write(file)