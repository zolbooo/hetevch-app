import Foundation

class Budget: ObservableObject {
    @Published var amount:Int = 10000
    @Published var date = Date()
    @Published var dailyBudget: Int = 1000
    
}
