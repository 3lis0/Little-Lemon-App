package com.example.littlelemon.AppFunctions

data class OrderItem(
    val name: String,
    val price : Double
)

class TaxCalculator  {
    companion object{
        const val salesTaxPercentage: Double  = 15.0

        fun getTaxAmountForOrderItems(orderItemList: List<OrderItem> ):Double {
            var orderAmount: Double = 0.0
            for (item in orderItemList){
                orderAmount += item.price
            }
            return (orderAmount * salesTaxPercentage) / 100
        }

        fun getNetAmountForOrderItem(orderItem: OrderItem):Double {
            return (orderItem.price + ((orderItem.price * salesTaxPercentage) / 100))
        }
    }
}