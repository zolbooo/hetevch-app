//
//  BudgetCardView.swift
//  Hetevch
//
//  Created by Zolboobayar Gantumur on 2023.06.24.
//  Copyright © 2023 Zolboobayar Gantumur. All rights reserved.
//

import SwiftUI

struct BudgetCardView: View {
    var amount: String
    var body: some View {
        HStack(alignment: .bottom) {
            VStack(alignment: .leading, spacing: 8) {
                Text("Миний зорилтот төлөвлөгөө").font(.caption).fontWeight(.light)
                Text(amount).font(.title2)
            }
            Spacer()
            Image("budget")
        }
    }
}

struct BudgetCardView_Previews: PreviewProvider {
    static var previews: some View {
        BudgetCardView(amount: "₮ 1,380,000.00")
    }
}
