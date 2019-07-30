# CSVProcessor
Input test.csv:

filename,origin,metadata,hash
file1,London,"a file about London",e737a6b0734308a08b8586720b3c299548ff77b846e3c9c89db88b63c7ea69b6
file2,Surrey,"a file about The National Archives",a4bf0d05d8805f8c35b633ee67dc10efd6efe1cb8dfc0ecdba1040b551564967
file55,London,"London was initially incorrectly spelled as Londom",e737a6b0734308a08b8586720b3c299548ff77b846e3c9c89db88b63c7ea69b6
file4,Penrith,"Lake District National Park info",a4bf0d05d8805f8c35b633ee67dc10efd6efe1cb8dfc0ecdba1040b551564968

Usage:
  java CSVProcessor "C:\\test.csv" origin Londom London
