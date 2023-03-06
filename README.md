# kotlin-blackjack

## 기능목록

### InputView
- [x] 게임에 참여할 플레이어들의 이름을 입력받는다.(쉼표 기준으로 분리)
  - 검증 어디에서 할건지?

### Card

### Cards
- [x] 승부를 결정할때는 21이 넘지않으면서 가장 가까운 합을 얻을 수 있도록 Ace를 1 또는 11로 계산
- [x] 게임이 시작하면 카드를 두장씩 받는다.
- [x] 카드의 합계가 특정 숫자를 넘는 것을 판단할 수 있다.
- [x] 카드의 합을 알 수 있다.

### GameResult
- [x] 두명의 점수를 비교하여 승패를 결정한다.

### BlackJackReferee
- [x] 최종 승패를 결정한다.

### Player
- [x] 플레이어는 들고있는 카드의 총합이 21이하라면 카드를 추가로 받을 수 있다.
- [x] 더 뽑을거냐고 물어볼때는 Ace 무조건 1로 계산
- [ ] `21초과로 죽음` 상태가 되면 커지면 배팅 금액을 모두 잃게 된다.
- [ ] 딜러와 플레이어가 모두 동시에 `블랙잭` 상태인 경우 플레이어는 베팅한 큼액을 돌려받는다.
- [ ] `블랙잭` 상태로 승리하게 되면 배팅 금액의 1.5배를 딜러에게 받는다.

### Dealer
- [x] 딜러는 들고있는 카드의 총합이 16이하라면 카드를 추가로 받는다.
- [ ] `21 초과로 죽음` 상태가 되면 커지면 플레이어들은 가지고 있는 패에 상관 없이 승리해 배팅 금액을 받는다.

### BattingMoney 
- [x] 0원보다 큰 금액을 배팅해야한다.

### Cards
- [x] 블랙잭
- [x] 진행중
- [x] 21 초과로 죽음
  
