//
//  CreateBudgetView.swift
//  Hetevch
//
//  Created by Zolboobayar Gantumur on 2023.06.24.
//  Copyright © 2023 Zolboobayar Gantumur. All rights reserved.
//

import SwiftUI
import CurrencyField

struct CreateBudgetView: View {
    @State private var amount: Int = 0
    @State private var endDate = Date()
    
    var onSave: (Int, Date) -> Void
    private func onSubmit() {
        onSave(amount, endDate)
    }
    
    private var formatter: NumberFormatter {
        let fmt = NumberFormatter()
        fmt.numberStyle = .currency
        fmt.minimumFractionDigits = 2
        fmt.maximumFractionDigits = 2
        fmt.locale = Locale(identifier: "mn-MN")
        return fmt
    }
    var body: some View {
        Form {
            let duration = ceil(Date().daysUntil(endDate: endDate)) + 1
            let formattedAmount = formatter.string(for: Double(amount) / 100 / duration)!
            BudgetCardView(amount: formattedAmount)
            Section(header: Text("Төсөв")) {
                CurrencyField(value: $amount, formatter: formatter)
                    .keyboardType(.numbersAndPunctuation)
                DatePicker(selection: $endDate, in: Date()..., displayedComponents: .date) { Text("Дуусах өдөр") }
            }
            Section {
                HStack {
                    Text("Өдрийн төсөв")
                    Spacer()
                    Text(formattedAmount)
                }
                Button(action: onSubmit) {
                    Text("Хадгалах")
                }
            }
        }.navigationTitle("Төсөв үүсгэх")
    }
}

struct CreateBudgetView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            CreateBudgetView(onSave: { _, _ in })
        }
    }
}
