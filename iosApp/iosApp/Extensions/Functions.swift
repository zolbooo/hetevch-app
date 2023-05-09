import Foundation

func countDays(from date:Date) -> Int {
    let calendar = Calendar.current
    let components = calendar.dateComponents([.day], from: Date(), to: date)
    return components.day ?? 0
}

func dailyBudget(amount: Int, chosenDate: Date) -> Int {
    let days = countDays1(startDate: Date.now, endDate: chosenDate)
    if days == 0 {
        return amount
    }
    let dailyAmount = amount / Int(days)
    return dailyAmount
}

func countDays1(startDate: Date, endDate: Date) -> Int {
    let calendar = Calendar.current
    let startComponents = calendar.dateComponents([.year, .month, .day], from: startDate)
    let endComponents = calendar.dateComponents([.year, .month, .day], from: endDate)
    
    guard let startDate = calendar.date(from: startComponents),
          let endDate = calendar.date(from: endComponents) else {
        return 0
    }
    
    let components = calendar.dateComponents([.day], from: startDate, to: endDate)
    
    return components.day! + 1
}


