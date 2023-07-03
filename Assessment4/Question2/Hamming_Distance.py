# Python3 program to find Hamming Distance between two strings - 21MIS1146

# Function to calculate Hamming distance
def hammingDist(str1, str2):
	i = 0
	count = 0
	#Exception to handle unequal string length
	if len(str1) != len(str2):raise ValueError ("Input strings must have equal length.")

	while(i < len(str1)):
		if(str1[i] != str2[i]):
			count += 1
		i += 1
	return count

# Driver code
str1 = "10101011"
str2 = "11001010"

# function call
print("Hamming Distance : ",hammingDist(str1, str2))
