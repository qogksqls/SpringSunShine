<template>
  <div class="overflow-auto text-center">
    미래에 할 예약들
    <b-table
      hover
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
      :fields="fields"
    ></b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "NO",
          sortable: true,
        },
        {
          key: "date",
          label: "날짜",
          soratable: true,
        },
        {
          key: "time",
          label: "시간",
          soratable: true,
        },
        {
          key: "ther",
          label: "상담사",
          soratable: true,
        },
        {
          key: "parent",
          label: "부모",
          soratable: true,
        },
        {
          key: "child",
          label: "아이이름",
          soratable: true,
        },
      ],
      items: [
        {
          NO: 1,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 2,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 3,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 4,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 5,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 6,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 7,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 8,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 9,
          date: "20220812 ",
          time: "17:00",
          ther: "어",
          parent: "부모",
          child: "아이",
        },
        {
          NO: 10,
          date: "20220812 ",
          time: "17:00",
          ther: "구",
          parent: "부모",
          child: "아이",
        },
      ],
    };
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
};
</script>
<style scoped>
#my-table {
  cursor: pointer;
}
</style>
