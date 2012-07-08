
del "req_data.txt"

del "output.dot"


del "output.jpg"

java -cp xerces-2.2.1.jar;. xmltreeview

java dotty

dot -T jpg -o output.jpg output.dot

output.jpg

exit

