import Foundation

class Expenses: ObservableObject {
    @Published var items = [ExpenseItem]()
    
    init() {
            let formatter = DateFormatter()
            formatter.dateFormat = "yyyy/MM/dd"
            
            items.append(ExpenseItem(amount: 100, date: formatter.date(from: "2022/01/01")!))
            items.append(ExpenseItem(amount: 200, date: formatter.date(from: "2022/01/02")!))
            items.append(ExpenseItem(amount: 300, date: formatter.date(from: "2022/01/03")!))
        }
    
}
