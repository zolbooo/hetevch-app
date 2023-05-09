import SwiftUI

struct GearView: View {
    @ObservedObject var budget = Budget()
//    let mongolianLocale = Locale(identifier: "mn")
    
    @State private var amount:Int = 0
    @State private var chosenDate = Date()
    @State var isShowingSuccessful = false
    @State private var showAlert = false
    

    
    func handleSubmit() {
        if amount != 0 {
            budget.amount = amount
            budget.date = chosenDate
            self.amount = 0
            self.chosenDate = Date()
            isShowingSuccessful = true
            showAlert = true
            hideKeyboard()
        } else {
            isShowingSuccessful = false
            showAlert = true
        }
        
    }
    

    
    var body: some View {
        VStack(spacing: 20) {
            HStack(alignment: .bottom, spacing: 8) {
                VStack(alignment: .leading, spacing: 8) {
                    Text("Миний зорилтот төлөвлөгөө")
                        .font(.custom("Inter-Regular", size: 12))
                        .foregroundColor(Color("LightGray"))
                    
                    Text("₮ \(amount)")
                        .font(.custom("Inter-Regular_Bold", size: 24))
                        .foregroundColor(Color.white)
                }
                
                Spacer()
                
                Image("illustration_1")
            }
            .padding()
            .background(
                RoundedRectangle(cornerRadius: 8)
                    .foregroundColor(Color.black)
            )
            
            
            VStack(spacing: 8) {
                HStack() {
                    VStack(alignment: .leading) {
                        Text("Төсөв")
                            .font(.custom("Inter-Regular", size: 12))
                            .foregroundColor(Color("LightGray"))
                        
                        //                        Text("₮ 380,000.00")
                        //                            .font(.custom("Inter-Regular_Medium", size: 18))
                        //                            .foregroundColor(Color.black)
                        TextField("Amount", value: $amount, formatter: numberFormatter)
                            .keyboardType(.numberPad)
                            .submitLabel(.done)
                        
                    }
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color("LightSmoke"), lineWidth: 1)
                )
                
                //Dropdown
                
                HStack() {
                    VStack(alignment: .leading) {
                        Text("Хугацаа")
                            .font(.custom("Inter-Regular", size: 12))
                            .foregroundColor(Color("LightGray"))
                        DatePicker("Өдөр сонгох", selection: $chosenDate,
                                   in: Date()..., displayedComponents: [.date]
                        )
                        .font(.custom("Inter-Regular", size:15))
                        .environment(\.locale, Locale.init(identifier: "mn"))
                        
                    }
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color("LightSmoke"), lineWidth: 1)
                )
                
                HStack() {
                    VStack(alignment: .leading) {
                        Text("Өдрийн төсөв")
                            .font(.custom("Inter-Regular", size: 12))
                            .foregroundColor(Color("LightGray"))
                        
                        Text("₮ \(dailyBudget(amount:(amount),chosenDate:chosenDate))")
                            .font(.custom("Inter-Regular_Medium", size: 18))
                            .foregroundColor(Color.black)
                    }
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .foregroundColor(Color("Gray"))
                )
                
            }
            
            Button(action: {
                handleSubmit()
            }) {
                HStack {
                    Spacer()
                    Text("Хадгалах")
                        .font(.custom("Inter-Regular_Bold", size: 18))
                        .foregroundColor(Color.white)
                    Spacer()
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 6)
                        .foregroundColor(Color.black)
                )
            }
            .alert(isShowingSuccessful ? "Амжилттай!" : "Төсвөө оруулна уу!", isPresented: $showAlert, actions: {
                Button("Ойлголоо", role: .cancel) {}
            })
            
            Spacer()
        }
        .toolbar {
                        ToolbarItemGroup(placement: .keyboard) {
                            Spacer()
                            Button {
                                hideKeyboard()
                            } label: {
                                Label("Dismiss", systemImage: "keyboard.chevron.compact.down")
                            }
                        }
                    }
        .padding()
    }
    
    let numberFormatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.locale = Locale.current
        formatter.currencySymbol = "₮"
        return formatter
    }()
    
}

struct GearView_Previews: PreviewProvider {
    static var previews: some View {
        GearView(budget: Budget())
    }
}
