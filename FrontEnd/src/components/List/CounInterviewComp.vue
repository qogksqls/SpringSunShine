<template>
  <!--상담사만 봄 상담일지 볼 탭-->
  <div class="overflow-auto  text-center">
    <b-table
      hover
      id="my-table2"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
      :fields="fields"
      ><router-link to="/counReview"></router-link>
    </b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table2"
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
          key: "content",
          label: "상담일지",
          soratable: false,
        },
      ],
      items: [
        { NO: 1, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 2, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 3, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 4, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 5, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 6, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 7, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 8, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 9, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 10, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
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
#my-table2 {
  cursor: pointer;
}
</style>
