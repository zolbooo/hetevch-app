import SwiftUI
import shared

struct ContentView: View {
    private var createBudgetUseCase = CreateBudgetUseCase()
    var body: some View {
        NavigationView {
            CreateBudgetView(viewModel: CreateBudgetViewModel(), onSave: { amount, endDate in
                let duration = ceil(Date().daysUntil(endDate: endDate)) + 1
                createBudgetUseCase.setBudget(amount: Int64(amount), duration: Int32(duration))
                // TODO: Navigate further
            })
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
