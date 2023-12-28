var current = null;
document.querySelector('#id').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: 0,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 2772',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#password').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -336,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#addr').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -672,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#name').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -1008,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#submit').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -1374,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '530 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
$(document).ready(function() {
  $('#checkDuplicateId').on('click', function() {
    const id = $('#id').val();
    if (id) {
      $.ajax({
        url: '/check-duplicate',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ id: id }),
        success: function(response) {
          if (response.isDuplicate) {
            $('#idCheckResult').text('이미 존재하는 아이디입니다.').css('color', 'red');
          } else {
            $('#idCheckResult').text('사용 가능한 아이디입니다.').css('color', 'green');
          }
        },
        error: function() {
          $('#idCheckResult').text('아이디 검증 중 오류가 발생했습니다.').css('color', 'orange');
        }
      });
    } else {
      $('#idCheckResult').text('아이디를 입력해주세요.').css('color', 'orange');
    }
  });

  $('#signupForm').on('submit', function(event) {
    event.preventDefault();
  });
});
