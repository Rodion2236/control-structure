import org.junit.Test

import org.junit.Assert.*
import ru.netology.calculateCommission

class ComissionsKtTest {

 // Тесты для Visa
 @Test
 fun calculateCommission_Visa_MinCommission() {
  val result = calculateCommission("Visa", 0, 1000)
  assertEquals(35, result) // Минимальная комиссия 35 руб.
 }

 @Test
 fun calculateCommission_Visa_AboveMin() {
  val result = calculateCommission("Visa", 0, 50_000)
  assertEquals(375, result) // 50000 * 0.0075 = 375
 }

 @Test
 fun calculateCommission_Visa_DayLimitExceeded() {
  val result = calculateCommission("Visa", 0, 150_001)
  assertNull(result) // Превышен дневной лимит (150_000)
 }

 @Test
 fun calculateCommission_Visa_MonthLimitExceeded() {
  val result = calculateCommission("Visa", 600_000, 1)
  assertNull(result) // Превышен месячный лимит (600_000)
 }

 // Тесты для MasterCard
 @Test
 fun calculateCommission_MasterCard_NoCommission() {
  val result = calculateCommission("MasterCard", 0, 75_000)
  assertEquals(0, result) // В пределах лимита (75_000)
 }

 @Test
 fun calculateCommission_MasterCard_CommissionOnExcess() {
  val result = calculateCommission("MasterCard", 74_000, 2_000)
  assertEquals(26, result) // (1000 * 0.006) + 20 = 26
 }

 @Test
 fun calculateCommission_MasterCard_FullCommission() {
  val result = calculateCommission("MasterCard", 75_000, 10_000)
  assertEquals(80, result) // (10000 * 0.006) + 20 = 80
 }

 // Тесты для Мир
 @Test
 fun calculateCommission_Mir_NoCommission() {
  val result = calculateCommission("Мир", 0, 150_000)
  assertEquals(0, result) // Нет комиссии
 }

 @Test
 fun calculateCommission_Mir_DayLimitExceeded() {
  val result = calculateCommission("Мир", 0, 150_001)
  assertNull(result) // Превышен дневной лимит
 }

 // Тесты для неизвестных карт
 @Test
 fun calculateCommission_UnknownCard() {
  val result = calculateCommission("AmericanExpress", 0, 3_000)
  assertNull(result) // Неизвестный тип карты
 }

 // Тест параметров по умолчанию
 @Test
 fun calculateCommission_DefaultParameters() {
  val result = calculateCommission()
  assertEquals(35, result) // Visa, 0, 3_000 → min комиссия
 }
}