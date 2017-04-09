import groovy.swing.SwingBuilder 
import javax.swing.* 
import java.awt.* 
import java.awt.geom.Point2D.Double
import java.util.zip.Adler32



public class Calculator {
	def myapp = new SwingBuilder()
	def digStack = []
	def opStack= []
	def tempNum = ""
	
	def operationsPrioMap = [p4:['(',')'],
							 p3:['*','/'],
							 p2:['+','-']]
	
	def getKeyByValue( val) {
		operationsPrioMap.find{
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
			  
			  button(text : '7', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '8', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '9', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '<', actionPerformed : {})
			  
			  button(text : '4', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '5', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '6', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '/', actionPerformed : {digPush(equation.text as double);opPush(it.source.text)})
			  
			  button(text : '1', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '2', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '3', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '*', actionPerformed : {digPush(equation.text as double);opPush(it.source.text)})
			  
			  button(text : '0', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '.', actionPerformed : {tempNum +=it.source.text; equation.text = tempNum;})
			  button(text : '+', actionPerformed : {evalWithPrio();digPush(equation.text as double);opPush(it.source.text)})
			  button(text : '-', actionPerformed : {evalWithPrio();digPush(equation.text as double);opPush(it.source.text)})

			  label(text : '')
			  button(text : '(', actionPerformed : {if(!equation.text.isEmpty()) digPush(equation.text as double);opPush(it.source.text)})
			  button(text : ')', actionPerformed : {digPush(equation.text as double);opPush(it.source.text);equation.text = compInBraces()})
			  button(text : '=', actionPerformed : {digPush(equation.text as double);equation.text = eval(opPop(), digPop(), digPop())})
			}
		}
		 myframe.setVisible(true)
	}	

	def digPush(val) {
		digStack.push(val)
		tempNum =""
	}
	double digPop(){
		digStack.pop()
	}
	
	def opPush(val) {
		opStack.push(val)
	}
	char opPop(){
		opStack.pop()
	}
	
	double eval(char operator, double dig1, double dig2) {
		def result
		switch (operator)
		{
			case "*":
				result = dig1*dig2
				break
			case "/":
				result = dig2/dig1
				break
			case "+":				
				result = dig1+dig2
				break
			case "-":
				result = dig2-dig1
				break
			default:
				break
		}
	}
	

	double compInBraces() {	
		double result
		if(opStack.last() == ')' && isOperand(opStack.getAt(opStack.lastIndexOf(opStack.last())-1))) {
			println opStack.pop()
			result = eval(opPop(), digPop(), digPop())
			opStack.pop()
		}
		result
	}
	
	boolean isOperand(operand) {
		if(operand=='*' || operand=='/' || operand=='+' || operand=='-' ) {
			true
		}
		else 
			false
	}

	double evalWithPrio(){
		def operand = opPop()
		if(getKeyByValue(operand) == "p3") {
			eval(operand, digPop(), digPop())
		}	
	}
}






