//
//  CreateBudgetView.swift
//  Hetevch
//
//  Created by Zolboobayar Gantumur on 2023.06.24.
//  Copyright © 2023 Zolboobayar Gantumur. All rights reserved.
//

import SwiftUI

struct CreateBudgetView: View {
    @State private var amount = ""
    @State private var endDate = Date()
    
    private let numberFormatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.minimum = .init(integerLiteral: 1)
        formatter.maximum = .init(integerLiteral: Int.max)
        formatter.generatesDecimalNumbers = false
        formatter.maximumFractionDigits = 0
        return formatter
    }()
    var body: some View {
        Form {
            BudgetCardView(amount: "₮ 380,000.00")
            Section(header: Text("Төсөв")) {
                TextField("Хэмжээ", value: $amount, formatter: numberFormatter)
                DatePicker(selection: $endDate, in: Date()..., displayedComponents: .date) { Text("Дуусах өдөр") }
            }
            Section {
                HStack {
                    Text("Өдрийн төсөв")
                    Spacer()
                    Text("₮ 38,000.00")
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
