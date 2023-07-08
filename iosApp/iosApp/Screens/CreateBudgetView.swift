//
//  CreateBudgetView.swift
//  Hetevch
//
//  Created by Zolboobayar Gantumur on 2023.06.24.
//  Copyright © 2023 Zolboobayar Gantumur. All rights reserved.
//

import SwiftUI
import CurrencyField

struct CreateBudgetView<VM : CreateBudgetViewModelProtocol>: View {
    @StateObject var viewModel: VM

    var onSave: (Int, Date) -> Void
    private func onSubmit() {
        onSave(viewModel.amount, viewModel.endDate)
    }
    
    var body: some View {
        Form {
            BudgetCardView(amount: viewModel.formattedAmount)
            Section(header: Text("Төсөв")) {
                CurrencyField(value: $viewModel.amount, formatter: viewModel.formatter)
                    .keyboardType(.numbersAndPunctuation)
                DatePicker(selection: $viewModel.endDate, in: Date()..., displayedComponents: .date) { Text("Дуусах өдөр") }
            }
            Section {
                HStack {
                    Text("Өдрийн төсөв")
                    Spacer()
                    Text(viewModel.dailyGoal)
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
            CreateBudgetView(viewModel: CreateBudgetViewModel(), onSave: { _, _ in })
        }
    }
}
