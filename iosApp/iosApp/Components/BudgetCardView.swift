//
//  BudgetCardView.swift
//  Hetevch
//
//  Created by Zolboobayar Gantumur on 2023.06.24.
//  Copyright © 2023 Zolboobayar Gantumur. All rights reserved.
//

import SwiftUI

struct BudgetCardView: View {
    @Binding var amount: Int
    var formatter: NumberFormatter

    var body: some View {
        HStack(alignment: .bottom) {
            VStack(alignment: .leading, spacing: 8) {
                Text("Миний зорилтот төлөвлөгөө").font(.caption).fontWeight(.light)
                Text(formatter.string(for: Double(amount) / 100)!).font(.title2)
            }
            Spacer()
            Image("budget")
        }
    }
}

struct BudgetCardView_Previews: PreviewProvider {
    static var previews: some View {
        @State var amount = 1300
        BudgetCardView(amount: $amount, formatter: {
            let fmt = NumberFormatter()
            fmt.numberStyle = .currency
            fmt.minimumFractionDigits = 2
            fmt.maximumFractionDigits = 2
            fmt.locale = Locale(identifier: "mn-MN")
            return fmt
        }())
    }
}
