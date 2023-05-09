import Foundation

struct ExpenseItem: Identifiable {
    var id = UUID()
    let amount: Int
    let date: Date
}
