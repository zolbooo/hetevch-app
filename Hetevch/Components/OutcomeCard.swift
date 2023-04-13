//
//  SwiftUIView.swift
//  Hetevch
//
//  Created by Bazarsad Uurdmandakh on 2023.04.07.
//

import SwiftUI

struct Card: Identifiable {
    let id = UUID()
    let value: String
    let dateTime: String
}

struct OutcomeCard: View {
    
    init() {
        UIScrollView.appearance().bounces = false
        UITableView.appearance().backgroundColor = .black
    }
    
    let cards: [Card] = [
        Card(value: "₮1000", dateTime: "Өнөөдөр, 16:24"),
        Card(value: "₮1000", dateTime: "Өнөөдөр, 16:24"),
        Card(value: "₮1000", dateTime: "Өнөөдөр, 16:24"),
        Card(value: "₮1000", dateTime: "Өнөөдөр, 16:24")
    ]
    
    var body: some View {
//        ScrollView(.vertical, showsIndicators: false) {
            
        List() {
                ForEach(cards) { card in
                    CardView(card: card)
                        .listRowBackground(Color("DarkGray"))
                        
                }
                .swipeActions(edge: .trailing, allowsFullSwipe: false) {
                    Button(action: {
                        
                    }) {
                        HStack {
                            Image(systemName: "trash.fill")
                            Text("Устгах")
                        }
                        
                    }
                    .tint(.red)
                }
            }
        .scrollContentBackground(.hidden)
        .background(.black)
            
//            VStack(spacing: 4) {
//                ForEach(cards) { card in
//                    CardView(card: card)
//
//                }
//            }
//                    .padding()
//        }

    }
    
    
}

struct CardView: View {
    let card: Card
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
                Text(card.value)
                    .font(.custom("Inter-Regular_Bold", size: 16))
                    .foregroundColor(Color.white)
//                    .padding(.bottom, 1.0)
                    
                Text(card.dateTime)
                    .font(.custom("Inter-Regular", size: 12))
                    .foregroundColor(Color("LightGray"))
                
            }
            .padding(20.0)
//        .padding(20)
    }
    
    
}

struct SwiftUIView_Previews: PreviewProvider {
    static var previews: some View {
        OutcomeCard()
    }
}
