import SwiftUI

struct HomeView: View {
    @ObservedObject var budget: Budget
    @State private var isShowingModal = false
    @StateObject var expenses = Expenses()
    @State private var dailybudget: Int = 0
    
 
    func removeItem(offsets: IndexSet) {
        for index in offsets {
                let expense = expenses.items[index]
                budget.amount += expense.amount
                // add expense amount back to budget
                expenses.items.remove(at: index) // remove item from list
            }
    }
    
    func getTimeAndMinute(from date: Date) -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "HH:mm"
        return formatter.string(from: date)
    }
    
    var body: some View {
        VStack {
            VStack {
                HStack {
                    Text("Өдрийн мэнд")
                        .font(.custom("Inter-Regular_SemiBold", size:20))
                        .foregroundColor(Color.black)
                    Spacer()
                }.padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0))
                
                HStack {
                    Text("₮ \(budget.amount) / \(countDays1(startDate: Date.now, endDate: budget.date)) өдөр")
                        .font(.custom("Inter-Regular", size:14))
                        .foregroundColor(.gray)
                    Spacer()
                }
                Spacer()
                
                HStack {
                    Text("Өнөөдрийн төсөв")
                        .font(.custom("Inter-Regular", size:14))
                        .foregroundColor(.gray)
                    Spacer()
                }
                
                HStack {
                    Text("₮ \(dailyBudget(amount:(budget.amount),chosenDate:budget.date))")
                        .font(.custom("Inter-Regular_Bold", size:60))
                        .onTapGesture{
                            isShowingModal = true
                        }
                        .foregroundColor(Color.black)
                        .sheet(isPresented: $isShowingModal, content: {
                            PlusView(isModalPresented: $isShowingModal,
                                     budget: budget, expenses: expenses
                            )
                        })
                        
                    Spacer()
                }
            }.padding()
                .background(Color.white)
            
            VStack {
                HStack {
                    Text("Зарлага")
                        .font(.custom("Inter-Regular_SemiBold", size:16))
                        .foregroundColor(.white)
                    Spacer()
                }
                .padding(20)
                
                if expenses.items.isEmpty {
                    VStack {
                        Text("Зарлага байхгүй байна.")
                    }.background(.black)
                    
                } else {
                    List() {
                        ForEach(expenses.items) { item in
                            VStack(alignment: .leading, spacing: 8) {
                                Text("₮ \(item.amount)")
                                    .font(.custom("Inter-Regular_Bold", size: 16))
                                    .foregroundColor(Color.white)
                                //                    .padding(.bottom, 1.0)
                                
                                Text("\(getTimeAndMinute(from:item.date))")
                                    .font(.custom("Inter-Regular", size: 12))
                                    .foregroundColor(Color("LightGray"))
                                
                            }
                            .padding(20.0)
                            .listRowBackground(Color("DarkGray"))
                            
                        }
                        .onDelete(perform: removeItem)
                        
                    }
                    .scrollContentBackground(.hidden)
                    .background(.black)
                }
                
                
            
            }
//            .padding(20)
            .background(Color.black)
            .frame(height: 400)
            
        }
        
    }
}

struct Home_Previews: PreviewProvider {
    static var previews: some View {
        HomeView(budget: Budget())
    }
}
