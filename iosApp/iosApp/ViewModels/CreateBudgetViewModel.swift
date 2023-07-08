//
//  CreateBudgetViewModel.swift
//  Hetevch
//
//  Created by Zolboobayar Gantumur on 2023.07.08.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

protocol CreateBudgetViewModelProtocol : ObservableObject {
    var amount: Int { get set }
    var formattedAmount: String { get }
    var endDate: Date { get set }
    var dailyGoal: String { get }
    
    var formatter: NumberFormatter { get }
}

final class CreateBudgetViewModel : CreateBudgetViewModelProtocol {
    var formatter: NumberFormatter {
        let fmt = NumberFormatter()
        fmt.numberStyle = .currency
        fmt.minimumFractionDigits = 2
        fmt.maximumFractionDigits = 2
        fmt.locale = Locale(identifier: "mn-MN")
        return fmt
    }
    
    @Published var amount: Int = 0
    var formattedAmount: String {
        formatter.string(for: Double(amount) / 100)!
    }
    
    @Published var endDate: Date = Date()
    
    var dailyGoal: String {
        let duration = ceil(Date().daysUntil(endDate: endDate)) + 1
        return formatter.string(for: Double(amount) / 100 / duration)!
    }
}
