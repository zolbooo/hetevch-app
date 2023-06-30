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
            BudgetCardView(amount: $amount, formatter: formatter)
            Section(header: Text("Төсөв")) {
                CurrencyField(value: $amount, formatter: formatter)
                    .keyboardType(.numbersAndPunctuation)
                DatePicker(selection: $endDate, in: Date()..., displayedComponents: .date) { Text("Дуусах өдөр") }
            }
            Section {
                HStack {
                    Text("Өдрийн төсөв")
                    Spacer()
                    Text(formatter.string(for: {
                        // See: https://stackoverflow.com/questions/62482308/conversion-from-nstimeinterval-to-days
                        let hours = Calendar.current.dateComponents([.hour], from: Date(), to: endDate).hour!
                        let days = ceil(Double(hours) / 24)
                        return Double(amount) / Double(days + 1) / 100
                    }())!)
                }
                Button(action: {}) {
                    Text("Хадгалах")
                }
            }
        }.navigationTitle("Төсөв үүсгэх")
    }
}

struct CreateBudgetView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView {
            CreateBudgetView()
        }
    }
}
