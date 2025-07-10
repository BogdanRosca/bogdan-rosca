# Exploratory testing of Monefy app 

## Used artifact: 
`[iOS](https://itunes.apple.com/us/app/monefy-money-manager/id1212024409?)`

## Exploratory testing charters

### Charter 1: Explore user signup
**Goal:** Validate new user signup flow

**Scope:** iOS app 

**Duration:** 5 min

**Notes / Findings:** 
Signup flow:
- Tested new user can finalise the register process
- Tested new user can start using the app without a subscription  
- Tested user is presented the Upgrade plan screen, upon application restart 
- Tested user can close the Upgrade plan screen and land on the account 

UX observations:
- The user is not able to go back to a previous step during signup 

### Charter 2: Explore adding income
**Goal:** Verify adding income flow

**Scope:** Add income flow(+), category selection, built-in calculator, balance update

**Out of scope:** Add recurring income

**Duration:** 20 min

**Notes / Findings:** 
- Test user can exit the add income flow through cancel button 
- Test user can add card income as salary and balance update
- Test user can add cash income as deposit and balance update
- Test user can add income as savings and balance update
- Tested free plan user is prompted to upgrade in order to add a new income category during the flow
- Tested user can add income with a note and balance update
- Tested user can add income without a note and balance update
- Tested user can add expense for a past date and balance update
- Tested user can add income for a future date and balance update
- Tested user cannot add income without value (tested with 0 and 0.00)
- Tested users cannot add negative income using the calculator (eg. 400 - 600)
- Tested users cannot add income higher than 9 digits using the calculator (eg. 40000*40000)
- Tested the calculator including edge cases (division by 0, floating numbers, 9 digit numbers)

Functionality observations:
- maximum input for income or expense is 9 digits. If the value overflows, for example when using the calculator (40000*40000) input value resets to 0 without any message towards the user.

UX / usability observations:
- changing between cash/card payment hard to identify as CTA element 

### Charter 3: Explore adding expense
**Goal:** Verify adding expense flow

**Scope:** Add expense flow(-), category selection, built-in calculator, balance update

**Out of scope:** Add recurring expense

**Duration:** 20 min

**Notes / Findings:** 
- Test user can exit the add expense flow through cancel button 
- Test user can add card expense with existing chategory and balance update
- Test user can add cash expense with existing chategory and balance update
- Tested free plan user is prompted to upgrade in order to add a new expense category during the flow
- Tested user can add expense with a note and balance update
- Tested user can add expense without a note and balance update
- Tested user can add expense for a past date and balance update
- Tested user can add expense for a future date and balance update
- Tested user cannot add expense without value (tested with 0 and 0.00)
- Tested users cannot add negative expense using the calculator (eg. 400 - 600)
- Tested users cannot add expense higher than 9 digits using the calculator (eg. 40000*40000)
- Tested negative balance (by adding big expense)

Functionality observations:
- maximum input for income or expense is 9 digits. If the value overflows, for example when using the calculator (40000*40000) input value resets to 0 without any message towards the user.

Security observations:
- no login mechanism in place. As the app handles sensitive data, any person having access to the unlocked phone can access it. 

UX / usability observations:
- changing between cash/card payment hard to identify as CTA element 


### Charter 4: Explore transactions history
**Goal:** Verify grouping of transactions

**Scope:** Transactions grouping by category, details of transactions behind a category, edit and deletion of transactions

**Duration:** 20 min

**Notes / Findings:** 
- Tested user can open transactions history by tapping "Balance"
- Tested user can close transactions history by tapping "Balance"
- Tested user can see income transaction groups in green
- Tested user can see expense transaction groups in red
- Tested subtracting all expenses from all income results in the balance 
- Tested transactions details behind an income category (value, date)
- Tested number of transactions for a category (circle element) is the same as the number of transactions behind that income category
- Tested transactions details behind an expense category (value, date)
- Tested number of transactions for a category (circle element) is the same as the number of transactions behind that expense category
- Tested sum of transactions for a category is the same as the total shown for the category 
- Tested grouping of expenses by category
- Tested grouping of expenses by date
- Tested editing of an income (amount, date, cash/card, category and note) and values after update
- Tested editing of an expense (amount, date, cash/card, category and note) and values after update
- Tested deleting of an income and balance update
- Tested deleting of an expense and balance update


## Charters prioritization
1. Registration, as an entry point into the app. Treated as a showstopper  
2. Adding/editing income expenses together with balance update. Core functionality. 
3. Transactions details with categorization. The most important feature of the app.

## Risks to mitigate
### Security and privacy risks, as users add data that is sensitive (income, salary, specific expenses).
| Risk     | Mitigation  |
|----------|----------|
| Leaked data | Encryption of data at rest |
| Data sent unencrypted | Encryption of data during transit | 
| Unauthorized access | Ensure only account owner can access the app, through a login mechanism | 


### Functional risks, as users can rely heavily on the numbers. 
| Risk     | Mitigation  |
|----------|----------|
| Errors in calculations | Automated tests to boost release confidence and avoid regressions |
| Data loss | Setup encrypted backups | 
| Data loss on crash | Auto-save drafts | 
| App crashes on large data | Test large datasets |
| Currency issues | Test multiple currencies and locales| 

### Compliance and data governance, as users add private information.
| Risk     | Mitigation  |
|----------|----------|
| GDPR non-compliance | Clear privacy policy. Mechanism to export/delete data |
