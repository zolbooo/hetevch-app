import Foundation

extension Date {
    func daysUntil(endDate: Date) -> Double {
        let hours = Calendar.current.dateComponents([.hour], from: Date(), to: endDate).hour!
        let days = ceil(Double(hours) / 24)
        return days
    }
}
