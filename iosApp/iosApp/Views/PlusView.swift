import SwiftUI

struct NumpadView: View {
    let rows: Int
    let columns: Int
    let keys: [[String]]
    
    @ObservedObject var input: NumpadInput
    
    var body: some View {
        VStack(spacing: 4) {
            ForEach(0..<rows, id: \.self) { row in
                HStack(spacing: 4) {
                    ForEach(0..<columns, id: \.self) { column in
                        
                        if keys[row][column] == "x" {
                            Button(action: {
                                
                                input.handleBackspace()
                                
                            }) {
                                Text(keys[row][column])
                                    .font(.custom("Inter-Regular_Bold", size:24))
                                    .frame(width: 100, height: 80)
                                    .background(Color("DarkGray"))
                                    .foregroundColor(.white)
                                    .cornerRadius(6)
                            }
                        }else {
                            
                            Button(action: {
                                
                                input.handleNumbers(number: keys[row][column])
                                // Handle button tap
                            }) {
                                Text(keys[row][column])
                                    .font(.custom("Inter-Regular_Bold", size:24))
                                    .frame(width: 100, height: 80)
                                    .background(Color("DarkGray"))
                                    .foregroundColor(.white)
                                    .cornerRadius(6)
                            }
                        }
                        
                    }
                }
                
            }
        }
        
    }
}

struct PlusView: View {
    
    let numpadRows = 4
    let numpadColumns = 3
    let numpadKeys: [[String]] = [
        ["1", "2", "3"],
        ["4", "5", "6"],
        ["7", "8", "9"],
        ["000", "0", "x"] // Empty string for empty key
        ]
        
    @ObservedObject var input = NumpadInput()
    @Binding var isModalPresented: Bool
    @ObservedObject var budget: Budget
    @State var InAmount: Int = 0
    @ObservedObject var expenses: Expenses
    
    func handleSubmit() {
    }
    
    var body: some View {
        VStack {
            HStack {
                Spacer()
                
                Text("Зарлага бүртгэх")
                    .font(.custom("Inter-Regular_SemiBold", size: 18))
                Spacer()
            }
            .padding(EdgeInsets(top: 30, leading:10, bottom: 20, trailing: 10))
            
            Spacer()
            
            HStack {
                Text("Үлдэгдэл ₮ \(budget.amount - (input.amount as NSString).integerValue)")
                    .font(.custom("Inter-Regular", size:14))
                    .foregroundColor(Color("LightGray"))
                Spacer()
            }
            
            .padding(.horizontal)
            
            HStack {
                Text("₮\(input.amount)")
                    .font(.custom("Inter-Regular_Bold", size:40))
                    .truncationMode(.tail)
                    
                Spacer()
            }
            .padding(.horizontal)
            
            VStack {
                NumpadView(rows: numpadRows, columns: numpadColumns, keys: numpadKeys, input: input)
                    .padding(EdgeInsets(top: 12, leading: 20, bottom: 8, trailing: 20))
                
                Button(action: {
                    
                    handleSubmit()
                    let item = ExpenseItem(amount: (input.amount as NSString).integerValue, date: Date.now)
                    expenses.items.append(item)
                    budget.amount = budget.amount - (input.amount as NSString).integerValue
                    isModalPresented = false
                    
                }) {
                        Spacer()
                        Text("Хадгалах")
                            .font(.custom("Inter-Regular_Bold", size: 18))
                            .foregroundColor(Color.black)
                        Spacer()
                    
                }
                .padding()
                .background(Color.white)
                .cornerRadius(6)
                
                Button(action: {
                    isModalPresented = false
                }) {
                        Spacer()
                        Text("Цуцлах")
                            .font(.custom("Inter-Regular_Bold", size: 18))
                            .foregroundColor(Color("LightGray"))
                        Spacer()
                    
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 6)
                        .stroke(Color("DarkGray"), lineWidth: 2)
                )
            }
            .padding()
            .background(Color.black)
            
            
        }
    }
        
}

struct PlusView_Previews: PreviewProvider {
    
    @Binding var isModalPresented: Bool
    @ObservedObject var budget: Budget
    
    static var previews: some View {
        PlusView(isModalPresented: .constant(true),budget: Budget(), expenses: Expenses())
    }
}

class NumpadInput: ObservableObject {
    @Published var amount = ""
    
    func handleNumbers(number: String) {
        if amount.count < 10 {
            amount.append(number)
        }
        
    }
    
    func handleBackspace() {
        if amount.count > 0 {
            amount.removeLast()
        }
    }
    
}
