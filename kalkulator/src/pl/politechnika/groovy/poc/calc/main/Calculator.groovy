import groovy.swing.SwingBuilder 
import javax.swing.* 
import java.awt.* 
import java.util.zip.Adler32



public class Calculator {
	def myapp = new SwingBuilder()
	def stack = []
	
	def operationsPrioMap = [p4:['(',')'],
							 p3:['*','/'],
							 p2:['+','-']]
	
	def getKeyByValue(map, val) {
		map.find{
			if(it.value.contains(val)) {
				it.find()?.key
				}
			}?.key
	}
	
	public static void main(String [ ] args) {
		new Calculator();
	}

	public Calculator() {
		 
		 def myframe = myapp.frame(title : 'Calculator', size : [400, 300], defaultCloseOperation : WindowConstants.EXIT_ON_CLOSE) {
			  borderLayout()
			  equation = textField(editable: false, horizontalAlignment:JTextField.RIGHT,constraints : BorderLayout.NORTH)
			  panel(constraints:BorderLayout.CENTER){
			  gridLayout(columns: 4, rows: 5)
			  
			  button(text : '7', actionPerformed : {equation.text += it.source.text; stack.push(it.source.text)})
			  button(text : '8', actionPerformed : {equation.text += it.source.text; println getKeyByValue(operationsPrioMap, '(')})
			  button(text : '9', actionPerformed : {equation.text += it.source.text})
			  button(text : '<', actionPerformed : {equation.text += it.source.text})
			  
			  button(text : '4', actionPerformed : {equation.text += it.source.text})
			  button(text : '5', actionPerformed : {equation.text += it.source.text})
			  button(text : '6', actionPerformed : {equation.text += it.source.text})
			  button(text : '/', actionPerformed : {equation.text += it.source.text})
			  
			  button(text : '1', actionPerformed : {equation.text += it.source.text})
			  button(text : '2', actionPerformed : {equation.text += it.source.text})
			  button(text : '3', actionPerformed : {equation.text += it.source.text})
			  button(text : '*', actionPerformed : {equation.text += it.source.text})
			  
			  button(text : '0', actionPerformed : {equation.text += it.source.text})
			  button(text : '.', actionPerformed : {equation.text += it.source.text})
			  button(text : '+', actionPerformed : {equation.text += it.source.text})
			  button(text : '-', actionPerformed : {equation.text += it.source.text})

			  label(text : '')
			  button(text : '(', actionPerformed : {equation.text += it.source.text;println(stack.first())})
			  button(text : ')', actionPerformed : {equation.text += it.source.text})
			  button(text : '=', actionPerformed : {equation.text += it.source.text})
			}
		}
		 myframe.setVisible(true)
	}	
}






