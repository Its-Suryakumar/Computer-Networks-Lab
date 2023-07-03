#CRC - 21MIS1146
def crc_error_detection(data, divisor):
    data = list(data)  # Convert the data string to a list of bits
    divisor_length = len(divisor)

    # Append zeros to the data string to match the divisor length
    data.extend(['0'] * (divisor_length - 1))

    # Perform polynomial division
    for i in range(len(data) - divisor_length + 1):
        if data[i] == '1':
            for j in range(divisor_length):
                data[i + j] = str(int(data[i + j]) ^ int(divisor[j]))

    # Return the remainder (CRC code)
    crc_code = ''.join(data[-divisor_length + 1:])

    return crc_code


def crc_data_transferred(data, polynomial):
    # Append zeros to the data to match the polynomial length
    data_bits = data + '0' * (len(polynomial) - 1)

    # Compute the CRC code
    crc_code = crc_error_detection(data_bits, polynomial)

    # Append the CRC code to the original data
    transmitted_data = data + crc_code

    return transmitted_data


data = '111011010011001'
polynomial = '101001'

transmitted_data = crc_data_transferred(data, polynomial)
print("Transmitted Data:", transmitted_data)
