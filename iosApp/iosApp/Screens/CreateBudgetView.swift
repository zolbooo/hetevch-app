//
//  CreateBudgetView.swift
//  Hetevch
//
//  Created by Zolboobayar Gantumur on 2023.06.24.
//  Copyright © 2023 Zolboobayar Gantumur. All rights reserved.
//

import SwiftUI
import SVGView

struct CreateBudgetView: View {
    var body: some View {
        VStack {
            BudgetCardView(amount: "₮ 380,000.00")
        }.padding()
    }
}

struct CreateBudgetView_Previews: PreviewProvider {
    static var previews: some View {
        CreateBudgetView()
    }
}
